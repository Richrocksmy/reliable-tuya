package org.richrocksmy.tuya.reliabletuya.output;

import org.springframework.data.util.Pair;

import java.util.List;
import java.util.function.Function;

public interface Output {

    void write(String output);

    <T> void write(List<T> things, Pair<List<String>, Function<T, List<String>>> columnElementProvider);
}
