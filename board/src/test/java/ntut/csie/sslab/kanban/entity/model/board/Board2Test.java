package ntut.csie.sslab.kanban.entity.model.board;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.Assert.assertEquals;

public class Board2Test {
    @Test
    public void test_creating_a_board_generating_a_domain_event(){
        Board2 board = new Board2(
                "teamId",
                "userId",
                UUID.randomUUID().toString(),
                "board");

        assertEquals(1, board.getDomainEvents().size() );
    }
}