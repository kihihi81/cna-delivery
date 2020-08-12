package local;

import local.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    DeliveryRepository deliveryRepository;



    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderd_Ship(@Payload Orderd orderd){
        //비동기 호출
        //SMS 기능 추가 ...

        if(orderd.isMe()){
            //System.out.println("##### listener Ship : " + orderd.toJson());

            Delivery delivery = new Delivery();
            delivery.setOrderId(orderd.getId());
            delivery.setStatus("SHIPPPING_READY");

            deliveryRepository.save(delivery);

        }
    }



}
