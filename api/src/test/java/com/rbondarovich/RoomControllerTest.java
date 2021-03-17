package com.rbondarovich;

import com.rbondarovich.service.bean.RoomBean;
import com.rbondarovich.testUtils.TestUtils;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
@TestPropertySource("/application-test.properties")
@Sql(value = "/data-start.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/data-finish.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void roomListTest() throws Exception {
        this.mockMvc.perform(get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"id\":1,\"name\":\"Roman room\",\"bulb\":false,\"country\":\"Belarus\"}," +
                                "{\"id\":2,\"name\":\"Roman room 2\",\"bulb\":false,\"country\":\"Belarus\"}," +
                                "{\"id\":3,\"name\":\"Tomas room\",\"bulb\":false,\"country\":\"Poland\"}," +
                                "{\"id\":4,\"name\":\"Vlad room\",\"bulb\":false,\"country\":\"Russia\"}]")
                );
    }

    @Test
    public void roomByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/rooms/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Roman room\",\"bulb\":false,\"country\":\"Belarus\"}")
                );
    }

    @Test
    public void saveRoomTest() throws Exception {
        RoomBean room = new RoomBean(5l, "ColdRoom", true, "Iceland");
        this.mockMvc.perform(TestUtils.postJson("/api/rooms", room));

        this.mockMvc.perform(get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"id\":1,\"name\":\"Roman room\",\"bulb\":false,\"country\":\"Belarus\"}," +
                                "{\"id\":2,\"name\":\"Roman room 2\",\"bulb\":false,\"country\":\"Belarus\"}," +
                                "{\"id\":3,\"name\":\"Tomas room\",\"bulb\":false,\"country\":\"Poland\"}," +
                                "{\"id\":4,\"name\":\"Vlad room\",\"bulb\":false,\"country\":\"Russia\"}," +
                                "{\"id\":5,\"name\":\"ColdRoom\",\"bulb\":true,\"country\":\"Iceland\"}]")
                );
    }

    @Test
    public void updateRoomTest() throws Exception {
        RoomBean room = new RoomBean(1l, "ColdRoom", true, "Iceland");

        this.mockMvc.perform(TestUtils.putJson("/api/rooms/1", room));

        this.mockMvc.perform(get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"id\":1,\"name\":\"Roman room\",\"bulb\":false,\"country\":\"Belarus\"}," +
                                "{\"id\":2,\"name\":\"Roman room 2\",\"bulb\":false,\"country\":\"Belarus\"}," +
                                "{\"id\":3,\"name\":\"Tomas room\",\"bulb\":false,\"country\":\"Poland\"}," +
                                "{\"id\":4,\"name\":\"Vlad room\",\"bulb\":false,\"country\":\"Russia\"}]")
                );
    }

    @Test
    public void deleteRoomTest() throws Exception {
        this.mockMvc.perform(delete("/api/rooms/1"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"id\":2,\"name\":\"Roman room 2\",\"bulb\":false,\"country\":\"Belarus\"}," +
                                "{\"id\":3,\"name\":\"Tomas room\",\"bulb\":false,\"country\":\"Poland\"}," +
                                "{\"id\":4,\"name\":\"Vlad room\",\"bulb\":false,\"country\":\"Russia\"}]")
                );
    }
}
