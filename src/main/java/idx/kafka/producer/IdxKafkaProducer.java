package idx.kafka.producer;

import idx.kafka.producer.DataRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.Producer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Collections;
import java.util.Optional;
import org.springframework.boot.ApplicationArguments;

@Service
public class IdxKafkaProducer {

    // Create topic in Confluent Cloud
    public static void createTopic(final String topic,
                                   final Properties cloudConfig) {
        final NewTopic newTopic = new NewTopic(topic, Optional.empty(), Optional.empty());
        try (final AdminClient adminClient = AdminClient.create(cloudConfig)) {
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
        } catch (final InterruptedException | ExecutionException e) {
            // Ignore if TopicExistsException, which may be valid if topic exists
            if (!(e.getCause() instanceof TopicExistsException)) {
                throw new RuntimeException(e);
            }
        }
    }

    @Autowired
    private org.springframework.boot.ApplicationArguments applicationArguments;
    public  void send(String url,String idxgroup, String topic_name, Integer idxTotal, Integer idxNumber, String path, String filename) throws IOException {

        // Load properties from a local configuration file
        // Create the configuration file (e.g. at '$HOME/.confluent/java.config') with configuration parameters
        // to connect to your Kafka cluster, which can be on your local host, Confluent Cloud, or any other cluster.
        // Follow these instructions to create this file: https://docs.confluent.io/platform/current/tutorials/examples/clients/docs/java.html
        final Properties props = loadConfig("kafka.config");

        // Create topic if needed
        final String topic = topic_name;
        createTopic(topic, props);

        // Add additional properties.
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaJsonSerializer");

        Producer<String, DataRecord> producer = new KafkaProducer<String, DataRecord>(props);


        DataRecord record = new DataRecord();

        record.setUrl(url);
        record.setDocument_current_number(idxNumber);
        record.setTotal_documents(idxTotal);
        record.setFilename(filename);
        record.setPath(path);

        System.out.printf("Producing record: %s\t%s%n", idxgroup, record);

        producer.send(new ProducerRecord<String, DataRecord>(topic,idxgroup , record), new Callback() {
                @Override
                public void onCompletion(RecordMetadata m, Exception e) {
                    if (e != null) {
                        e.printStackTrace();
                    } else {
                        System.out.printf("Produced record to topic %s partition [%d] @ offset %d%n", m.topic(), m.partition(), m.offset());
                    }
                }
            });


        producer.flush();

        System.out.printf("messages were produced to topic %s%n", topic);

        producer.close();
    }

    @Autowired
    private org.springframework.boot.ApplicationArguments applicationArgumentst;
    public  void sendToken(String token,String topic_name) throws IOException {

        // Load properties from a local configuration file
        // Create the configuration file (e.g. at '$HOME/.confluent/java.config') with configuration parameters
        // to connect to your Kafka cluster, which can be on your local host, Confluent Cloud, or any other cluster.
        // Follow these instructions to create this file: https://docs.confluent.io/platform/current/tutorials/examples/clients/docs/java.html
        final Properties props = loadConfig("kafka.config");

        // Create topic if needed
        final String topic = topic_name;
        createTopic(topic, props);

        // Add additional properties.
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaJsonSerializer");

        Producer<String, TokenRecord> producer = new KafkaProducer<String, TokenRecord>(props);


        TokenRecord record = new TokenRecord();

        record.setToken(token);

        System.out.println(token);
        producer.send(new ProducerRecord<String, TokenRecord>(topic,"xxxx",record), new Callback() {
            @Override
            public void onCompletion(RecordMetadata m, Exception e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    System.out.printf("Produced record to topic %s partition [%d] @ offset %d%n", m.topic(), m.partition(), m.offset());
                }
            }
        });


        producer.flush();

        System.out.printf("messages were produced to topic %s%n", topic);

        producer.close();
    }

    public static Properties loadConfig(final String configFile) throws IOException {
        if (!Files.exists(Paths.get(configFile))) {
            throw new IOException(configFile + " not found.");
        }
        final Properties cfg = new Properties();
        try (InputStream inputStream = new FileInputStream(configFile)) {
            cfg.load(inputStream);
        }
        return cfg;
    }

}
