package ms.commons.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by davor on 28/05/15.
 */
public class ObjectUtilsTest {

    private static class TestSource {

        private String name;

        private Integer age;

        private int size;

        private boolean employed;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isEmployed() {
            return employed;
        }

        public void setEmployed(boolean employed) {
            this.employed = employed;
        }
    }

    private static class TestDestination {

        private String name;

        private Integer age;

        private int size;

        private boolean employed;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isEmployed() {
            return employed;
        }

        public void setEmployed(boolean employed) {
            this.employed = employed;
        }
    }

    @Test
    public void testCopyValues() throws Exception {
        TestSource source = new TestSource();
        source.setName("test");
        source.setAge(30);
        source.setSize(404);
        source.setEmployed(true);

        TestDestination destination = new TestDestination();


        ObjectUtils.mapValues(source, destination);

        assertEquals(source.getName(), destination.getName());
        assertEquals(source.getAge(), destination.getAge());
        assertEquals(source.getSize(), destination.getSize());
        assertEquals(source.isEmployed(), destination.isEmployed());
    }
}