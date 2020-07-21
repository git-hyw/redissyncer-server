package syncer.syncerservice.filter.filter_factory;

import lombok.Builder;
import syncer.syncerplusredis.model.TaskModel;
import syncer.syncerservice.filter.*;
import syncer.syncerservice.util.JDRedisClient.JDRedisClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanenqiang
 * @Description 只全量策略工厂
 * @Date 2020/3/18
 */
@Builder
public class StockonlyFilterListFactory implements KeyValueFilterListFactory {
    @Override
    public List<CommonFilter> getStrategyList(TaskModel taskModel, JDRedisClient client) {
        List<CommonFilter> strategyList = new ArrayList<>();
        strategyList.add(KeyValueTimeCalculationFilter.builder().taskId(taskModel.getId()).client(client).build());
        strategyList.add(KeyValueDataAnalysisFilter.builder().taskId(taskModel.getId()).client(client).build());
        strategyList.add(KeyValueEventDBMappingFilter.builder().taskId(taskModel.getId()).client(client).build());
//            commonFilterList.add(KeyValueSizeCalulationFilter.builder().taskId(taskId).client(client).build());
        strategyList.add(KeyValueRdbSyncEventFilter.builder().taskId(taskModel.getId()).client(client).redisVersion(taskModel.getRedisVersion()).build());
        return strategyList;
    }
}
