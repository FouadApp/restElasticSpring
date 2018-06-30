package com.fouad.api.restElasticSpring.resources;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@RestController
@RequestMapping("/api")
public class EventResources {

    TransportClient client;

    public EventResources() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

    }

    @GetMapping("/apps")
    public List<EventWithStatus> view() {
        SearchResponse response = client.prepareSearch("applications")
                .setTypes("stat")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(matchAllQuery())
                .setFrom(0).setSize(150)
                .execute()
                .actionGet();

        //// methode 1 sans fait appel a EventWithState
/*
  Map<String, Object> ff = new HashMap<String, Object>();
        for (SearchHit hit : response.getHits()) {

            System.out.println(hit.getSourceAsMap().values());

           ff.put("Status",hit.getSourceAsMap());

        }
        System.out.println(ff.size());
        return ff.values();*/

///// methode 2 Appel a EventWithState
        List<EventWithStatus> listEvents = new ArrayList<EventWithStatus>();

        for (SearchHit hit : response.getHits()) {

            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();
            String source = hit.getSourceAsString();
            Map<String, Object> feild = hit.getSourceAsMap();

            String key = String.valueOf(feild.get("key"));
            String timeStampString = String.valueOf(feild.get("timeStamp"));
            String status = String.valueOf(feild.get("status"));
            String appType = String.valueOf(feild.get("appType"));
            String topic = String.valueOf(feild.get("topic"));
            String uuld = String.valueOf(feild.get("Uuld"));


            EventWithStatus eventWithStatus = new EventWithStatus(key, timeStampString, status, appType, topic,uuld);
            listEvents.add(eventWithStatus);
        }

        listEvents.forEach(e -> System.out.println(e.toString()));
        // client.close();
        return  listEvents;

    }

}