package com.example.task2_fetch_json_data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {


    long id, userId ; String title , body;


//"""
//    {
//        "userId" : 1,
//            "id" : 1,
//            "title" : "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//            "body" : "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
//    }
//"""
}
