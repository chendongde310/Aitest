package cn.com.cdgame.aitest.alice;

import android.content.Context;

import cn.com.cdgame.aitest.modle.JobModle;

/**
 * 职业
 */

public class Job {

    String jobId;//职业ID
    String jobName; //职业名称
    String jobLv;//等级

    public Job(String jobId, String jobName, String jobLv) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobLv = jobLv;
    }

    public static Job base() {
        return new Job("job000000", "无业", "0");
    }


    public static Job loadXml(Context context, String jobPath) {

        Job job = Job.base();

// TODO: 2017/7/24
        JobModle.get
        return job;
    }
}
