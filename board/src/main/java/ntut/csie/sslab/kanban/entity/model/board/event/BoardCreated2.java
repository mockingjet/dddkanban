package ntut.csie.sslab.kanban.entity.model.board.event;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;

public class BoardCreated2 extends DomainEvent {

	private final String teamId;
	private final String boardId;
	private final String name;
	private final String userId;

	public BoardCreated2(String teamId, String boardId, String name, String userId) {
		super(DateProvider.now());
		this.teamId = teamId;
		this.boardId = boardId;
		this.name = name;
		this.userId = userId;
	}

	public String getTeamId() {
		return teamId;
	}

	public String getBoardId() {
		return boardId;
	}

	public String getName() {
		return name;
	}

	public String getUserId() {
		return userId;
	}
}
