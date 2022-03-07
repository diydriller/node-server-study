# spring

## 1. Controller

* response에 @Getter가 없으면 객체를 json으로 변환하는 과정에서 에러가 발생한다.

* json을 dto객체로 변환하는 과정에서 @NoArgConstructor가 없으면 에러가 발생한다. 

* interceptor에서 controller로 값을 넘겨주고 싶은 경우 HttpServletRequest의
attribute를 사용한다.

## 2. Build

* linux에서 빌드할때 권한이 없다는 뜨는 경우 실행권한을 추가해준다.
```shell
    sudo su 
    chmod +x gradlew
```

## 3. Validation

* @NotNull은 null을 허용하지 않는다. @NotEmpty는 null과 비어있는 것을 허용하지 않는다.
@NotBlank는 null과 비어있는 것과 " "을 허용하지 않는다.
* bean에서 validation 사용시에는 @Validated와 @Valid를 붙여야한다.

## 4. Bean

* spring에서는 static 필드에 @Value를 지원하지 않기때문에 set{필드이름}Static 메서드를
사용한다.
``` java
    @RestController
    public class PropertyController {  
        private static String NAME_STATIC;
    
        @Value("${name}")
        public void setNameStatic(String name){
            PropertyController.NAME_STATIC = name;
        }
    }
```

## 5. Annotation
* @ConfigurationProperties는 설정파일에서 key value 값을 멤버변수로 매핑시켜준다.
