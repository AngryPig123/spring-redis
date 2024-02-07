package org.spring.redis.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class TestInfo {

    private String val1;
    private String val2;

    private TestInfo() {
    }

    @Builder
    public TestInfo(String val1, String val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestInfo testInfo = (TestInfo) o;
        return Objects.equals(val1, testInfo.val1) && Objects.equals(val2, testInfo.val2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val1, val2);
    }

}
