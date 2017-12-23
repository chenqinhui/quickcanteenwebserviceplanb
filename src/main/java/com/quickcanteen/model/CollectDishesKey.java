package com.quickcanteen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CollectDishesKey {
    private Integer dishesId;

    private Integer collectorId;

   public CollectDishesKey(int dishesId,int collectorId){
       this.dishesId = dishesId;
       this.collectorId = collectorId;
   }
}