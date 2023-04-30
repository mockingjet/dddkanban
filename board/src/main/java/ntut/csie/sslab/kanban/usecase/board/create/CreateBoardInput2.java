package ntut.csie.sslab.kanban.usecase.board.create;

public class CreateBoardInput2 {
    private String name;
    private String teamId;
    private String userId;

    public CreateBoardInput2(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
