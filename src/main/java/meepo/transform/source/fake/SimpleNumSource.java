package meepo.transform.source.fake;

import meepo.transform.channel.DataEvent;
import meepo.transform.channel.RingbufferChannel;
import meepo.transform.config.TaskContext;
import meepo.transform.source.AbstractSource;

/**
 * Created by peiliping on 17-3-6.
 */
public class SimpleNumSource extends AbstractSource {

    public SimpleNumSource(String name, int index, TaskContext context, RingbufferChannel rb) {
        super(name, index, context, rb);
    }

    @Override public Object[] eventFactory() {
        return new Long[2];
    }

    @Override public void work() {
        DataEvent de = super.feedOne();
        de.getSource()[0] = Long.valueOf(super.indexOfSources);
        de.getSource()[1] = super.tmpIndex;
        super.channel.pushBySeq(super.tmpIndex);
    }
}