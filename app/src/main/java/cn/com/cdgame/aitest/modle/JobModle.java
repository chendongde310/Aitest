package cn.com.cdgame.aitest.modle;

import android.content.Context;

import org.dom4j.Element;

import cn.com.cdgame.aitest.alice.Job;

/**
 * Author：陈东
 * Time：2017/7/24 - 下午4:35
 * Notes:职业模型
 */

public class JobModle extends BaseModle<Job> {

    private static final String HOST_PATH = "job";
    private static final String[] JOB_PATH = {"战士","刺客"};


    JobModle(Context context) {
        super(context);
    }

    @Override
    protected String getHostPath() {
        return HOST_PATH;
    }


    @Override
    String[] getDataPath() {
        return JOB_PATH;
    }

    @Override
    protected Job Xml2Bean(Element e) {
        Job job = Job.base();
        job.setJobId(e.attributeValue("id"));
        job.setJobName(e.attributeValue("name"));
        job.setJobLv(e.attributeValue("lv"));
        job.setJobDepict(e.attributeValue("depict"));
        for (Element attr : e.elements("attribute")) {
            job.addAttribute(new Job.Attribute(attr.attributeValue("name"), attr.attributeValue("value")));
        }
        return job;
    }


}
