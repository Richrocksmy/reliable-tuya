package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class TuyaCommand {

    public enum TuyaCommandEntry {
        LIGHT_ON(Map.of("code", "switch_led", "value", true)),
        LIGHT_OFF((Map.of("code", "switch_led", "value", false)));

        private final Map<String, Object> entry;

        TuyaCommandEntry(final Map<String, Object> entry) {
            this.entry = entry;
        }

        public Map<String, Object> asInterfaceType() {
            return entry;
        }
    }

    private static final String COMMAND_KEY = "commands";

    private final List<TuyaCommandEntry> commands;

    private TuyaCommand(final List<TuyaCommandEntry> commands) {
        this.commands = commands;
    }

    public Map<String, Object> asInterfaceType() {
        return Map.of(COMMAND_KEY, commands.stream().map(TuyaCommandEntry::asInterfaceType).toList());
    }

    public static class Builder {

        private List<TuyaCommandEntry> commands = new ArrayList<>();

        public TuyaCommand.Builder addCommand(final TuyaCommandEntry tuyaCommandEntry) {
            commands.add(tuyaCommandEntry);
            return this;
        }

        public TuyaCommand build() {
            return new TuyaCommand(commands);
        }
    }
}
