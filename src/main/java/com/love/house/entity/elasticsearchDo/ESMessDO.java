package com.love.house.entity.elasticsearchDo;

import com.love.house.model.FieldAnalyzer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: wy
 * @Date: 2021/2/23 11:06
 * @Description: 消息存储列表
 */
@Document(indexName = "message", // 索引名
        type = "message", // 类型。未来的版本即将废弃
        shards = 1, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
public class ESMessDO {

    @Id
    private Integer id;

    /**
     * 发言人
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
    private String name;

    /**
     * 发言时间
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
    private String speakTime;

    /**
     * 发言内容
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.Text)
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeakTime() {
        return speakTime;
    }

    public void setSpeakTime(String speakTime) {
        this.speakTime = speakTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
