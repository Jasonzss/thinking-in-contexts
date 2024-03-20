package com.jason.tics.common.security.component;

import com.jason.tics.common.security.adapter.PublicUrlsAdapter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 无需权限访问的Url注册在此
 *
 * @author Jason
 */
@Component
public class DefaultPublicUrlsAdapter implements PublicUrlsAdapter {
    @Getter
    @Setter
    private List<String> UrlsWithNoAuthentication = new ArrayList<>();


    @Override
    public List<String> listUrlsWithNoAuthentication() {
        return UrlsWithNoAuthentication;
    }
}
