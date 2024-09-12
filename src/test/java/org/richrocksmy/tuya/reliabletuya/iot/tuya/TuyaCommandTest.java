package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaCommand.TuyaCommandEntry.LIGHT_OFF;
import static org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaCommand.TuyaCommandEntry.LIGHT_ON;

class TuyaCommandTest {
    private static final String COMMAND_TEMPLATE = """
            {"commands":[{"code":"%s","value":%s}]}""";

    @Test
    @SuppressWarnings("unchecked")
    void shouldCreateLightOnCommand() throws Exception {
        TuyaCommand tuyaCommand = new TuyaCommand.Builder()
                .addCommand(LIGHT_ON)
                .build();

        List<Map<String, Object>> tuyaCommands = (List<Map<String, Object>>) tuyaCommand.asInterfaceType().get("commands");
        assertThat(tuyaCommands).satisfiesExactly(it -> {
            assertThat(it).containsEntry("code", "switch_led").containsEntry("value", true);
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCreateLightOffCommand() throws Exception {
        TuyaCommand tuyaCommand = new TuyaCommand.Builder()
                .addCommand(LIGHT_OFF)
                .build();

        List<Map<String, Object>> tuyaCommands = (List<Map<String, Object>>) tuyaCommand.asInterfaceType().get("commands");
        assertThat(tuyaCommands).satisfiesExactly(it -> {
            assertThat(it).containsEntry("code", "switch_led").containsEntry("value", false);
        });
    }
}