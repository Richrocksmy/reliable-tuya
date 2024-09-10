package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TuyaCommand {

    public enum TuyaCommandEntry {
        LIGHT_ON(Map.of("code", "switch_led", "value", true)),
        LIGHT_OFF((Map.of("code", "switch_led", "value", false)));

        private final Map<String, Object> entry;

        private TuyaCommandEntry(final Map<String, Object> entry) {
            this.entry = entry;
        }

        public JsonElement asJson() {
            return new Gson().toJsonTree(entry);
        }
    }

    private static final String COMMAND_KEY = "commands";

    private final List<TuyaCommandEntry> commands;

    private TuyaCommand(final List<TuyaCommandEntry> commands) {
        this.commands = commands;
    }

    public List<TuyaCommandEntry> getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        commands.forEach(it -> jsonArray.add(it.asJson()));
        jsonObject.add(COMMAND_KEY, jsonArray);

        return jsonObject.toString();
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
