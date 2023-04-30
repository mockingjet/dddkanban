package ntut.csie.sslab.kanban.usecase.board.create;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.board.BoardRepository2;
import ntut.csie.sslab.kanban.entity.model.board.Board2;

import java.util.UUID;

public class CreateBoardUseCase2 {

	BoardRepository2 repo;
	DomainEventBus eventBus;

	public CreateBoardUseCase2(BoardRepository2 repo, DomainEventBus eventBus) {
		this.repo = repo;
		this.eventBus = eventBus;
	}

	public void execute(CreateBoardInput2 input, CreateBoardOutput2 output) {
		String boardId = UUID.randomUUID().toString();

		Board2 board = new Board2(
				input.getTeamId(),
				boardId,
				input.getName(),
				input.getUserId()
		);

		output.setBoardId(boardId);

		repo.addBoard(board);
		eventBus.postAll(board);
	}
}
