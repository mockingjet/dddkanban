package ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.board;

import ntut.csie.sslab.kanban.entity.model.board.Board2;

import java.util.ArrayList;
import java.util.Optional;

public class BoardRepository2 {

    private ArrayList<Board2> store = new ArrayList<Board2>();

    public BoardRepository2(){
    }

    public Optional<Board2> findById(String id) {
        return store.stream().filter(b -> b.getId().equals((id))).findAny();
    }

    public void addBoard(Board2 board) {
        store.add(board);
    }
}
