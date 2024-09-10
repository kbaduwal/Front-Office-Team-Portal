package in.kb.binding;

import lombok.Data;

@Data
public class DashBoardResponse {
    private Integer totalEnquiriesSent;
    private Integer enrolledCnt;
    private Integer lostCnt;
}
