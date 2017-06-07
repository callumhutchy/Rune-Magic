@echo off

IF %1 == dev (
    gradlew -q setDevVersion
    gradlew build
) else ( if %1 == patch (
    gradlew -q incrementPatch
    gradlew build
    ) else ( if %1 == minor (
        gradlew -q incrementMinor
        gradlew build
        ) else ( if %1 == major (
            gradlew -q incrementMajor
            gradlew build
            ) else (
                echo Can't detect proper args. Exiting ...
                goto end
            )
        )
    )
)
:end
