package com.jason.tics.exercise.config;

import com.jason.tics.common.rocketmq.config.RocketMqAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jason
 */
@RefreshScope
@Configuration
public class RocketMqConfig {
    @Autowired
    private RocketMqAdapter adapter;
}
