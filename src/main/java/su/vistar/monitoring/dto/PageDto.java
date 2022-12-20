package su.vistar.monitoring.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageDto {
    private int pageNumber = 0;
    private int pageSize = 20;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private String sortBy = "id";

    public PageDto(int pageSize, int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Pageable getPageable() {
        return PageRequest.of(
                this.pageNumber,
                this.pageSize,
                this.sortDirection,
                this.sortBy);
    }
}
