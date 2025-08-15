package com.soundnest.util;


import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    @FunctionalInterface
    public interface Iterable<T> {
        void iterate(T data);
    }

    @FunctionalInterface
    public interface RowMapper<T> {
        T map(RowRecord row);
    }

    public static class RowRecord extends ArrayList<String> {
        private int cursor = 0;

        public RowRecord() {
            super();
        }

        public RowRecord(String[] data) {
            super(Arrays.asList(data));
        }

        public void resetCursor() {
            this.cursor = 0;
        }

        public String next() {
            return this.get(cursor++);
        }

        public <T> T parseNext(Class<T> type) {
            String value = next();
            if (type == String.class) {
                return type.cast(value);
            } else if (type == Integer.class || type == int.class) {
                return type.cast(Integer.parseInt(value));
            } else if (type == Boolean.class || type == boolean.class) {
                return type.cast(Boolean.parseBoolean(value));
            } else if (type == LocalDate.class) {
                return type.cast(LocalDate.parse(value));
            } else if (type == Double.class || type == double.class) {
                return type.cast(Double.parseDouble(value));
            } else if (type == Long.class || type == long.class) {
                return type.cast(Long.parseLong(value));
            }

            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

    public static class CSVRecord {
        private final List<RowRecord> data;

        protected CSVRecord() {
            this.data = new ArrayList<>();
        }

        protected void addRow(RowRecord row) {
            this.data.add(row);
        }

        public <T> void forEachRow(RowMapper<T> mapper, Iterable<T> consumer) {
            for (RowRecord row : data) {
                row.resetCursor();

                T mapped = mapper.map(row);
                consumer.iterate(mapped);

                row.resetCursor();
            }
        }

        public <T> void forEachRowWhere(RowMapper<T> mapper, Iterable<T> consumer) {
            for (RowRecord row : data) {
                row.resetCursor();

                T mapped = mapper.map(row);
                if (mapped != null) {
                    consumer.iterate(mapped);
                }

                row.resetCursor();
            }
        }

        public Long getLastId() {
            if (data.isEmpty() || data.get(data.size() - 1).isEmpty()) {
                return null;
            }
            return Long.parseLong(data.get(data.size() - 1).get(0));
        }
    }

    public static CSVRecord readCSV(String fileName) throws IOException {
        return readCSV(fileName, true);
    }

    public static CSVRecord readCSV(String fileName, boolean skipHeader) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);

        CSVRecord record = new CSVRecord();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            if (skipHeader) {
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                record.addRow(new RowRecord(data));
            }
        }
        return record;
    }
}