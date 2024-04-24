package cn.asany.flowable.core.graphql.resolver;

import cn.asany.flowable.core.filters.TaskStatus;
import cn.asany.flowable.core.service.TaskInfoService;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.Date;
import java.util.List;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.stereotype.Component;

/**
 * 任务信息
 *
 * @author limaofeng
 */
@Component
public class TaskInfoGraphQLResolver implements GraphQLResolver<TaskInfo> {

  private final TaskInfoService taskInfoService;

  public TaskInfoGraphQLResolver(TaskInfoService taskInfoService) {
    this.taskInfoService = taskInfoService;
  }

  public Date startTime(TaskInfo taskInfo) {
    if (taskInfo instanceof HistoricTaskInstance) {
      return ((HistoricTaskInstance) taskInfo).getStartTime();
    }
    return null;
  }

  public Date endTime(TaskInfo taskInfo) {
    if (taskInfo instanceof HistoricTaskInstance) {
      return ((HistoricTaskInstance) taskInfo).getEndTime();
    }
    return null;
  }

  public Long durationInMillis(TaskInfo taskInfo) {
    if (taskInfo instanceof HistoricTaskInstance) {
      return ((HistoricTaskInstance) taskInfo).getDurationInMillis();
    }
    return null;
  }

  public TaskStatus status(TaskInfo taskInfo) {
    return TaskStatus.CREATED;
  }

  public List<Comment> comments(TaskInfo taskInfo, String type) {
    return taskInfoService.getTaskComments(taskInfo.getId(), type);
  }
}
