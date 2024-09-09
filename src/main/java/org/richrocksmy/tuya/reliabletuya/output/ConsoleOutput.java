package org.richrocksmy.tuya.reliabletuya.output;

import de.vandermeer.asciitable.AsciiTable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ConsoleOutput implements Output {

    @Override
    public void write(String output) {
        System.out.println(output);
    }

    @Override
    public <T> void write(List<T> things, Pair<List<String>, Function<T, List<String>>> columnElementProvider) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow(columnElementProvider.getFirst());
        asciiTable.addRule();

        things.forEach(it -> {
            asciiTable.addRow(columnElementProvider.getSecond().apply(it));
            asciiTable.addRule();
        });

        asciiTable.addRule();
        write(asciiTable.render());
    }
}
