# 启动Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# 启动Kafka
bin/kafka-server-start.sh config/server.properties

# 创建Topic
bin/kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 3 --topic topic-test

# Topic列表
bin/kafka-topics.sh --list --zookeeper 127.0.0.1:2181

# 查看Topic
bin/kafka-topics.sh --describe --zookeeper 127.0.0.1:2181 --topic topic-test

# 删除Topic
bin/kafka-topics.sh --delete --zookeeper 127.0.0.1:2181 --topic topic-test

# 消费Topic
bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic topic-test

# 查看Segment文件
bin/kafka-run-class.sh kafka.tools.DumpLogSegments --files /tmp/kafka-logs/topic-test-0/00000000000000000000.log --print-data-log
