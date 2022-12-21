package com.gamenawa.eeeeweeee.global.util.json;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GsonParserTest {
    private IJsonParser parser;

    @BeforeEach
    public void beforeEach() {
        parser = new GsonParser();
    }

    private static class Dummy {
        private final String name;
        private final int year;

        public Dummy(String name, int year) {
            this.name = name;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public int getYear() {
            return year;
        }
    }

    @Test
    void testToJson() {
        Dummy dummy = new Dummy("testName", 1996);
        String dummyJson = parser.toJson(dummy);
        assertThat(dummyJson).isEqualTo("{\"name\":\"testName\",\"year\":1996}");
    }

    @Test
    void testFromJson() {
        Dummy dummy = parser.fromJson("{\"name\":\"testName\",\"year\":1996}", Dummy.class);
        Dummy expected = new Dummy("testName", 1996);
        assertThat(dummy.getName()).isEqualTo(expected.getName());
        assertThat(dummy.getYear()).isEqualTo(expected.getYear());
    }
}
