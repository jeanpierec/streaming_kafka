import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Collections;
import java.util.Properties;

public class Consumidor {
    public static void main(String[] args) {
        // Configuración del consumidor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // dirección de tu servidor Kafka.
        props.put("group.id", "mi-grupo");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Suscribirse al tema 'registros'
        consumer.subscribe(Collections.singletonList("registros"));

        // Leer eventos en bucle
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Mensaje: " + record.value());
            }
        }
    }
}
