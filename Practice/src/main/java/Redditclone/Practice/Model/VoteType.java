package Redditclone.Practice.Model;

import Redditclone.Practice.Exception.SpringRedditException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;


@Getter

public enum VoteType {
    UPVOTE(1),DOWNVOTE(-1),;

    private int direction;
    VoteType(int direction){}

//    public static VoteType lookup(Integer direction){
//        return Arrays.stream(VoteType.values())
//                .filter(value->value.getDirection().equals(direction))
//                .findAny()
//                .orElseThrow(()->new SpringRedditException("vote not found"));
//    }
}
