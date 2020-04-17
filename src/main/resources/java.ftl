package ${packageName};

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ${className} {

  <#list fieldEntities as field>
  private ${field.fieldType} ${field.fieldName};
  </#list>
}