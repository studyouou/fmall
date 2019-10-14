package org.og.fmall.shopping.api.dto;

import lombok.Data;
import org.og.fmall.commonapi.dto.ResponseContext;

/**
 * @author:ougen
 * @date:2019/10/1122:41
 */
@Data
public class PayResponse extends ResponseContext {
    private String html;
}
