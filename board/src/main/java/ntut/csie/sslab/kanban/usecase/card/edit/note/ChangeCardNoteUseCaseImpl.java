package ntut.csie.sslab.kanban.usecase.card.edit.note;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.entity.model.card.Card;
import ntut.csie.sslab.kanban.usecase.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.usecase.card.CardRepository;

public class ChangeCardNoteUseCaseImpl implements ChangeCardNoteUseCase {

    private CardRepository cardRepository;
    private DomainEventBus domainEventBus;

    public ChangeCardNoteUseCaseImpl(CardRepository cardRepository, DomainEventBus domainEventBus) {
        this.cardRepository = cardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public void execute(ChangeCardNoteInput input, CqrsCommandOutput output) {
        Card card = cardRepository.findById(input.getCardId()).orElse(null);
        if (null == card){
            output.setId(input.getCardId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Change card estimate failed: card not found, card id = " + input.getCardId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return;
        }

        card.changeNotes(input.getNewNotes(), input.getUserId(), input.getUsername(), input.getBoardId());

        cardRepository.save(card);
        domainEventBus.postAll(card);

        output.setId(card.getCardId());
        output.setExitCode(ExitCode.SUCCESS);
    }

    @Override
    public ChangeCardNoteInput newInput() {
        return new ChangeCardNoteInputImpl();
    }

    private static class ChangeCardNoteInputImpl implements ChangeCardNoteInput {

        private String cardId;
        private String newNotes;
        private String userId;
        private String username;
        private String boardId;

        @Override
        public String getCardId() {
            return cardId;
        }

        @Override
        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        @Override
        public String getNewNotes() {
            return newNotes;
        }

        @Override
        public void setNewNotes(String newNotes) {
            this.newNotes = newNotes;
        }

        @Override
        public String getUserId() {
            return userId;
        }

        @Override
        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String getBoardId() {
            return boardId;
        }

        @Override
        public void setBoardId(String boardId) {
            this.boardId = boardId;
        }

    }



}
