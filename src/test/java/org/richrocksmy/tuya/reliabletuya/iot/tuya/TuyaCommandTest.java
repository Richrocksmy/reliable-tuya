package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaCommand.TuyaCommandEntry.LIGHT_OFF;
import static org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaCommand.TuyaCommandEntry.LIGHT_ON;

class TuyaCommandTest {

    // Example json structure for a command -
    private static final String COMMAND_TEMPLATE = """
            {"commands":[{"code":"%s","value":%s}]}""";

    @Test
    void shouldCreateLightOnCommand() throws Exception {
        TuyaCommand tuyaCommand = new TuyaCommand.Builder()
                .addCommand(LIGHT_ON)
                .build();

        String commandAsJson = tuyaCommand.toString();

        String lightOffCommand = COMMAND_TEMPLATE.formatted("switch_led", "true");
        JSONAssert.assertEquals(lightOffCommand, commandAsJson, JSONCompareMode.LENIENT);
    }

    @Test
    void shouldCreateLightOffCommand() throws Exception {
        TuyaCommand tuyaCommand = new TuyaCommand.Builder()
                .addCommand(LIGHT_OFF)
                .build();

        String commandAsJson = tuyaCommand.toString();

        String lightOnCommand = COMMAND_TEMPLATE.formatted("switch_led", "false");
        JSONAssert.assertEquals(lightOnCommand, commandAsJson, JSONCompareMode.LENIENT);
    }
}