TARGETS = console-setup resolvconf mountkernfs.sh ufw screen-cleanup apparmor hostname.sh plymouth-log udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh urandom checkroot.sh lvm2 networking open-iscsi iscsid checkfs.sh mountdevsubfs.sh mountnfs-bootclean.sh mountnfs.sh bootmisc.sh kmod mountall.sh checkroot-bootclean.sh mountall-bootclean.sh procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
networking: resolvconf mountkernfs.sh urandom procps
open-iscsi: networking iscsid
iscsid: networking
checkfs.sh: cryptdisks checkroot.sh lvm2
mountdevsubfs.sh: mountkernfs.sh udev
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
bootmisc.sh: mountnfs-bootclean.sh udev mountall-bootclean.sh checkroot-bootclean.sh
kmod: checkroot.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
mountall-bootclean.sh: mountall.sh
procps: mountkernfs.sh udev
