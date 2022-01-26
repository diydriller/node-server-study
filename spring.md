# spring

## 1. controller

* response에 @Getter가 없으면 객체를 json으로 변환하는 과정에서 에러가 발생한다.

* json을 dto객체로 변환하는 과정에서 @NoArgConstructor가 없으면 에러가 발생한다. 

* interceptor에서 controller로 값을 넘겨주고 싶은 경우 HttpServletRequest의
attribute를 사용한다.

## 2. build

* linux에서 빌드할때 권한이 없다는 뜨는 경우 실행권한을 추가해준다.
```shell
    sudo su 
    chmod +x gradlew
```

## 3. validation

* @NotNull은 null을 허용하지 않는다. @NotEmpty는 null과 비어있는 것을 허용하지 않는다.
@NotBlank는 null과 비어있는 것과 " "을 허용하지 않는다.
* bean에서 validation 사용시에는 @Validated와 @Valid를 붙여야한다.

