package ntut.csie.sslab.kanban.application.springboot.web.config;

import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.board.BoardRepositoryImpl;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.board.BoardRepositoryPeer;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.card.CardRepositoryImpl;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.card.CardRepositoryPeer;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.workflow.WorkflowRepositoryImpl;
import ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.workflow.WorkflowRepositoryPeer;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.usecase.board.BoardRepository;
import ntut.csie.sslab.kanban.usecase.card.CardRepository;
import ntut.csie.sslab.kanban.usecase.workflow.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:/application.properties")
@Configuration("KanbanRepositoryInjection")
public class RepositoryInjection {

  private BoardRepositoryPeer boardRepositoryPeer;

  private WorkflowRepositoryPeer workflowRepositoryPeer;

  private CardRepositoryPeer cardRepositoryPeer;

  @Autowired
  public void setBoardRepositoryPeer(BoardRepositoryPeer boardRepositoryPeer){
    this.boardRepositoryPeer = boardRepositoryPeer;
  }

  @Autowired
  public void setWorkflowRepositoryPeer(WorkflowRepositoryPeer workflowRepositoryPeer) {
    this.workflowRepositoryPeer = workflowRepositoryPeer;
  }

  @Autowired
  public void setCardRepositoryPeer(CardRepositoryPeer cardRepositoryPeer) {
    this.cardRepositoryPeer = cardRepositoryPeer;
  }


  @Bean(name="boardRepository")
  public BoardRepository boardRepository() {
    return new BoardRepositoryImpl(boardRepositoryPeer);
  }

  @Bean(name="workflowRepository")
  public WorkflowRepository workflowRepository() {
    return new WorkflowRepositoryImpl(workflowRepositoryPeer);
  }

  @Bean(name="cardRepository")
  public CardRepository cardRepository() {
    return new CardRepositoryImpl(cardRepositoryPeer);
  }


  @Bean(name="kanbanEventBus")
  public DomainEventBus eventBus() {
    return new GoogleEventBus();
  }

}
