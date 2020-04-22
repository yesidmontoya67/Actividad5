package co.edu.ff.orders.product.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.serialization.NumberSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class TaxRate implements NumberSerializable {
    BigDecimal value;

    private TaxRate(BigDecimal value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0 && value.compareTo(new BigDecimal("1"))<=0);  //El compareTo retorna 0 si el numeor es igual retorna -1 si es menor y retorna 1 si es mayor
        this.value = value;
    }

    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
