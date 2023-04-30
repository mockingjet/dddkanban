package ntut.csie.sslab.kanban.entity.model.board;

import ntut.csie.sslab.ddd.model.AggregateRoot;
import ntut.csie.sslab.kanban.entity.model.board.event.BoardCreated2;

public class Board2 extends AggregateRoot<String> {

	private String name;
	private String teamId;
	private String userId;

	public Board2(String teamId, String boardId, String name, String userId) {
		super(boardId);
		this.name = name;
		this.teamId = teamId;
		this.userId = userId;

		this.addDomainEvent(new BoardCreated2(teamId,  boardId,name, userId));
	}

	public String getName() {
		return name;
	}

	public String getTeamId() {
		return teamId;
	}

	public String getUserId() {
		return userId;
	}
}
