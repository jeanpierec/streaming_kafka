import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Productor {
    public static void main(String[] args) {
        // Configuración del productor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Servidor Kafka.
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Enviar eventos al tema 'registros'
        for (int i = 1; i <= 10; i++) {
            String mensaje = "Evento #" + i;
            producer.send(new ProducerRecord<>("registros", Integer.toString(i), mensaje));
        }

        producer.close();
    }
}
