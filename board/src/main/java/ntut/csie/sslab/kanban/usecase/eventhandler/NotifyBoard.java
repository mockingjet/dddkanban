package ntut.csie.sslab.kanban.usecase.eventhandler;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.entity.model.board.Board;
import ntut.csie.sslab.kanban.usecase.board.BoardRepository;
import ntut.csie.sslab.kanban.entity.model.workflow.event.WorkflowCreated;
import ntut.csie.sslab.kanban.entity.model.workflow.event.WorkflowDeleted;

import java.util.Optional;

public class NotifyBoard {
    private BoardRepository boardRepository;
    private DomainEventBus domainEventBus;

    public NotifyBoard(BoardRepository boardRepository,
                       DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.domainEventBus = domainEventBus;
    }

    public void whenWorkflowCreated(WorkflowCreated workflowCreated) {
        Optional<Board> board = boardRepository.findById(workflowCreated.boardId());
        if (!board.isPresent())
            throw new RuntimeException("Board not found, board id = " + workflowCreated.boardId());

        board.get().commitWorkflow(workflowCreated.workflowId());
        boardRepository.save(board.get());

        domainEventBus.postAll(board.get());
    }

    public void whenWorkflowDeleted(WorkflowDeleted workflowDeleted) {
        Optional<Board> board = boardRepository.findById(workflowDeleted.boardId());
        if (!board.isPresent())
            throw new RuntimeException("Board not found, board id = " + workflowDeleted.boardId());

        board.get().uncommitworkflow(workflowDeleted.workflowId());
        boardRepository.save(board.get());

        domainEventBus.postAll(board.get());
    }


    

}
