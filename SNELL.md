# Snell (personal build)

Branch: `feature/snell-support`

Depends on forked core: https://github.com/Minis233/exclave-core/tree/feature/snell-support

`library/core/go.mod` replaces:

```
replace github.com/exclavenetwork/exclave-core/v5 => github.com/Minis233/exclave-core v0.0.0-20260710024930-e59e6dd5cbaf
```

## Build (on a machine with JDK + Go 1.26 + Android NDK)

```bash
# clone both forks
git clone -b feature/snell-support https://github.com/Minis233/Exclave.git
git clone -b feature/snell-support https://github.com/Minis233/exclave-core.git

# rebuild libexclavecore AAR
cd Exclave/library/core
# if you prefer local core instead of remote replace:
# echo 'replace github.com/exclavenetwork/exclave-core/v5 => ../../../exclave-core' >> go.mod
go mod tidy
bash build.sh   # needs gomobile; writes app/libs/libexclavecore.aar

# assemble APK
cd ../..
./gradlew :app:assembleOssRelease
```

## Config

```json
{
  "protocol": "snell",
  "settings": {
    "address": "1.2.3.4",
    "port": 44046,
    "psk": "your-psk",
    "obfs": "off",
    "version": 4,
    "reuse": true
  }
}
```

Share link: `snell://psk@host:port?version=4&obfs=off&reuse=1#name`

**v6 not supported.** Use snell-server v4/v5 or OpenSnell.
