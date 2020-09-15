package com.huan.social.models.responseApi;
import com.huan.social.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ResponseFriend {
    private Long id;

    private String nickName;

    private String email;// Dung email de login

    private String phoneNumber;

    private String avatar;

    private String coverPhoto;

    private String friendStatus;

    public ResponseFriend() {
    }
}
