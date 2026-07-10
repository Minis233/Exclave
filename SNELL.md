# Snell (personal build)

- App branch: `feature/snell-support`
- Core: https://github.com/Minis233/exclave-core/tree/feature/snell-support

`library/core/go.mod` uses a **local** replace:

```
replace github.com/exclavenetwork/exclave-core/v5 => ../../exclave-core
```

CI checks out `Minis233/exclave-core@feature/snell-support` into `./exclave-core`.

## Local build

```bash
git clone -b feature/snell-support https://github.com/Minis233/Exclave.git
git clone -b feature/snell-support https://github.com/Minis233/exclave-core.git Exclave/exclave-core
cd Exclave && ./run lib core debug && ./gradlew :app:assembleOssDebug
```

Snell **v4/v5** only (not v6).
