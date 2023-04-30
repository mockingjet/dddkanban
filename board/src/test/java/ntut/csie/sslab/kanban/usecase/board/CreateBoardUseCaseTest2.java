package ntut.csie.sslab.kanban.usecase.board;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.entity.model.board.event.BoardCreated2;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import ntut.csie.sslab.kanban.usecase.board.create.*;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.board.BoardRepository2;


public class CreateBoardUseCaseTest2 {

    @Test
    public void test_create_board() {
        BoardRepository2 repo = new BoardRepository2();
        FakeEventListener fakeEventListener = new FakeEventListener();
        DomainEventBus domainEventBus = new GoogleEventBus();
        domainEventBus.register(fakeEventListener);
        CreateBoardUseCase2 useCase = new CreateBoardUseCase2(repo, domainEventBus);

        CreateBoardInput2 input = new CreateBoardInput2();
        CreateBoardOutput2 output = new CreateBoardOutput2();
        input.setTeamId("teamId");
        input.setUserId("userId");
        input.setName("board");

        useCase.execute(input, output);

        assertNotNull(output.getBoardId());
        assertNotNull(repo.findById(output.getBoardId()));
        assertEquals(1, fakeEventListener.notifyCount);
    }

    private class FakeEventListener {
        public int notifyCount = 0;

        @Subscribe
        public void whenBoardCreated(BoardCreated2 event) {
            this.notifyCount++;
        }
    }
}
