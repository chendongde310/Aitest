package cn.com.cdgame.aitest.alice;

import java.util.ArrayList;
import java.util.List;

/**
 * 职业
 */

public class Job {

    String jobId;//职业ID
    String jobName; //职业名称
    String jobLv;//等级
    String jobDepict;//职业描述
    List<Attribute> attributes;

    public Job(String jobId, String jobName, String jobLv) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobLv = jobLv;
        this.jobDepict = "";
        this.attributes = new ArrayList<>();
    }

    public static Job base() {
        return new Job("job000000", "无业", "0");
    }



    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobLv() {
        return jobLv;
    }

    public void setJobLv(String jobLv) {
        this.jobLv = jobLv;
    }

    public String getJobDepict() {
        return jobDepict;
    }

    public void setJobDepict(String jobDepict) {
        this.jobDepict = jobDepict;
    }


    public static class Attribute {
        String name;
        int value;

        public Attribute(String name, String value) {
            if (name != null) {
                this.name = name;
                this.value = Integer.parseInt(value);
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
