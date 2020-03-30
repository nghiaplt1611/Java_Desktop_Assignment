USE [master]
GO
/****** Object:  Database [QLKHO]    Script Date: 3/27/2020 11:53:12 PM ******/
CREATE DATABASE [QLKHO]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLKHO', FILENAME = N'E:\Down\a\New folder\QLKHO.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLKHO_log', FILENAME = N'E:\Down\a\New folder\QLKHO_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [QLKHO] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLKHO].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLKHO] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLKHO] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLKHO] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLKHO] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLKHO] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLKHO] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QLKHO] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLKHO] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLKHO] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLKHO] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLKHO] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLKHO] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLKHO] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLKHO] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLKHO] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QLKHO] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLKHO] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLKHO] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLKHO] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLKHO] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLKHO] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLKHO] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLKHO] SET RECOVERY FULL 
GO
ALTER DATABASE [QLKHO] SET  MULTI_USER 
GO
ALTER DATABASE [QLKHO] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLKHO] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLKHO] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLKHO] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QLKHO] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLKHO', N'ON'
GO
ALTER DATABASE [QLKHO] SET QUERY_STORE = OFF
GO
USE [QLKHO]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [QLKHO]
GO
/****** Object:  User [nghia]    Script Date: 3/27/2020 11:53:12 PM ******/
CREATE USER [nghia] WITHOUT LOGIN WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[CALAMVIEC]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CALAMVIEC](
	[STTca] [char](1) NOT NULL,
	[TGBDca] [time](0) NULL,
	[TGKTca] [time](0) NULL,
	[Luongcuaca] [int] NULL,
	[Ghichuca] [varchar](20) NULL,
 CONSTRAINT [PK__CALAMVIE__FF3D48791D2BA42F] PRIMARY KEY CLUSTERED 
(
	[STTca] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHITIETKIEMKE]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHITIETKIEMKE](
	[IDHH] [char](10) NOT NULL,
	[IDphieukiemke] [char](10) NOT NULL,
	[IDNV] [char](10) NOT NULL,
	[SLhienco] [int] NULL,
	[TinhtrangHH] [varchar](20) NULL,
	[Thoigiankiem] [smalldatetime] NULL,
	[Ghichukiem] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[IDphieukiemke] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHITIETNHAPKHO]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHITIETNHAPKHO](
	[IDHH] [char](10) NOT NULL,
	[IDNV] [char](10) NOT NULL,
	[IDphieunhap] [char](10) NOT NULL,
	[SLnhap] [int] NULL,
	[Thoigiannhap] [smalldatetime] NULL,
	[Ghichunhap] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[IDphieunhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHITIETXUATKHO]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHITIETXUATKHO](
	[IDphieuxuat] [char](10) NOT NULL,
	[IDHH] [char](10) NOT NULL,
	[IDNV] [char](10) NOT NULL,
	[IDDD] [char](10) NOT NULL,
	[Thoigianxuat] [smalldatetime] NULL,
	[SLxuat] [int] NULL,
	[Ghichuxuat] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[IDphieuxuat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CUNGCAP]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CUNGCAP](
	[IDHH] [char](10) NOT NULL,
	[IDnhaCC] [char](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[IDHH] ASC,
	[IDnhaCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DDNHAN]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DDNHAN](
	[IDDD] [char](10) NOT NULL,
	[TenDDnhan] [varchar](30) NULL,
	[DCnhan] [varchar](100) NULL,
	[SDTDDnhan] [char](15) NULL,
	[IDKH] [char](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[IDDD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HANGHOA]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HANGHOA](
	[IDHH] [char](10) NOT NULL,
	[TenHH] [varchar](30) NULL,
	[SLtoithieu] [int] NULL,
	[DVT] [varchar](10) NULL,
	[Dongianhap] [int] NOT NULL,
	[Dongiaxuat] [int] NOT NULL,
 CONSTRAINT [PK__HANGHOA__B87C1A0376FF6DD6] PRIMARY KEY CLUSTERED 
(
	[IDHH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[IDKH] [char](10) NOT NULL,
	[TenKH] [varchar](30) NULL,
	[SDTKH] [char](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[IDKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHACUNGCAP]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHACUNGCAP](
	[IDnhaCC] [char](10) NOT NULL,
	[TennhaCC] [varchar](30) NULL,
	[DiachinhaCC] [varchar](100) NULL,
	[SDTnhaCC] [char](15) NULL,
	[EmailnhaCC] [varchar](50) NULL,
 CONSTRAINT [PK__NHACUNGC__53BA596CBF470885] PRIMARY KEY CLUSTERED 
(
	[IDnhaCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[IDNV] [char](10) NOT NULL,
	[TenNV] [varchar](30) NULL,
	[Gioitinh] [char](7) NULL,
	[DiachiNV] [varchar](100) NULL,
	[SDTNV] [char](15) NULL,
	[Ngaybatdaulam] [date] NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](32) NOT NULL,
 CONSTRAINT [PK__NHANVIEN__B87DC9B2CBE28D68] PRIMARY KEY CLUSTERED 
(
	[IDNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TKADMIN]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TKADMIN](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](32) NOT NULL,
 CONSTRAINT [PK_TKADMIN] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TRUC]    Script Date: 3/27/2020 11:53:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TRUC](
	[IDNV] [char](10) NOT NULL,
	[STTca] [char](1) NOT NULL,
 CONSTRAINT [PK__TRUC__378E1D35A76343DF] PRIMARY KEY CLUSTERED 
(
	[IDNV] ASC,
	[STTca] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CALAMVIEC] ([STTca], [TGBDca], [TGKTca], [Luongcuaca], [Ghichuca]) VALUES (N'1', CAST(N'06:00:00' AS Time), CAST(N'12:00:00' AS Time), 100000, N' ')
INSERT [dbo].[CALAMVIEC] ([STTca], [TGBDca], [TGKTca], [Luongcuaca], [Ghichuca]) VALUES (N'2', CAST(N'12:00:00' AS Time), CAST(N'18:00:00' AS Time), 100000, N' ')
INSERT [dbo].[CALAMVIEC] ([STTca], [TGBDca], [TGKTca], [Luongcuaca], [Ghichuca]) VALUES (N'3', CAST(N'18:00:00' AS Time), CAST(N'23:30:00' AS Time), 150000, N' ')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH001     ', N'KK001     ', N'NV003     ', 15, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH002     ', N'KK002     ', N'NV003     ', 50, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH003     ', N'KK003     ', N'NV003     ', 20, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH004     ', N'KK004     ', N'NV003     ', 18, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH005     ', N'KK005     ', N'NV003     ', 20, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH006     ', N'KK006     ', N'NV003     ', 20, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH007     ', N'KK007     ', N'NV003     ', 20, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH008     ', N'KK008     ', N'NV003     ', 30, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH009     ', N'KK009     ', N'NV003     ', 40, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETKIEMKE] ([IDHH], [IDphieukiemke], [IDNV], [SLhienco], [TinhtrangHH], [Thoigiankiem], [Ghichukiem]) VALUES (N'HH010     ', N'KK010     ', N'NV003     ', 40, N'Tot', CAST(N'2020-10-02T22:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH001     ', N'NV001     ', N'PN001     ', 20, CAST(N'2019-10-02T07:00:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH002     ', N'NV001     ', N'PN002     ', 50, CAST(N'2019-10-02T07:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH003     ', N'NV001     ', N'PN003     ', 20, CAST(N'2019-10-02T07:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH004     ', N'NV002     ', N'PN004     ', 20, CAST(N'2019-10-02T13:00:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH005     ', N'NV002     ', N'PN005     ', 20, CAST(N'2019-10-02T13:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH006     ', N'NV002     ', N'PN006     ', 20, CAST(N'2019-10-02T14:00:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH007     ', N'NV002     ', N'PN007     ', 20, CAST(N'2019-10-02T13:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH008     ', N'NV003     ', N'PN008     ', 40, CAST(N'2019-10-02T18:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH009     ', N'NV003     ', N'PN009     ', 40, CAST(N'2019-10-02T19:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH010     ', N'NV003     ', N'PN010     ', 40, CAST(N'2019-10-02T20:30:00' AS SmallDateTime), N'')
INSERT [dbo].[CHITIETNHAPKHO] ([IDHH], [IDNV], [IDphieunhap], [SLnhap], [Thoigiannhap], [Ghichunhap]) VALUES (N'HH005     ', N'NV003     ', N'PN011     ', 29, CAST(N'2020-03-27T09:54:00' AS SmallDateTime), NULL)
INSERT [dbo].[CHITIETXUATKHO] ([IDphieuxuat], [IDHH], [IDNV], [IDDD], [Thoigianxuat], [SLxuat], [Ghichuxuat]) VALUES (N'PX001     ', N'HH001     ', N'NV001     ', N'DD001     ', CAST(N'2019-10-02T09:00:00' AS SmallDateTime), 5, N'')
INSERT [dbo].[CHITIETXUATKHO] ([IDphieuxuat], [IDHH], [IDNV], [IDDD], [Thoigianxuat], [SLxuat], [Ghichuxuat]) VALUES (N'PX002     ', N'HH004     ', N'NV002     ', N'DD002     ', CAST(N'2019-10-02T15:00:00' AS SmallDateTime), 2, N'')
INSERT [dbo].[CHITIETXUATKHO] ([IDphieuxuat], [IDHH], [IDNV], [IDDD], [Thoigianxuat], [SLxuat], [Ghichuxuat]) VALUES (N'PX003     ', N'HH008     ', N'NV003     ', N'DD003     ', CAST(N'2019-10-02T19:30:00' AS SmallDateTime), 10, N'')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH001     ', N'CC001     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH002     ', N'CC002     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH003     ', N'CC003     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH004     ', N'CC004     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH005     ', N'CC005     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH006     ', N'CC006     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH007     ', N'CC007     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH008     ', N'CC007     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH009     ', N'CC008     ')
INSERT [dbo].[CUNGCAP] ([IDHH], [IDnhaCC]) VALUES (N'HH010     ', N'CC009     ')
INSERT [dbo].[DDNHAN] ([IDDD], [TenDDnhan], [DCnhan], [SDTDDnhan], [IDKH]) VALUES (N'DD001     ', N'Tap hoa A', N'So 1, Mau Than, Can Tho', N'0351112222     ', N'KH001     ')
INSERT [dbo].[DDNHAN] ([IDDD], [TenDDnhan], [DCnhan], [SDTDDnhan], [IDKH]) VALUES (N'DD002     ', N'Tap hoa B', N'So 2, Mau Than, Can Tho', N'0352223333     ', N'KH002     ')
INSERT [dbo].[DDNHAN] ([IDDD], [TenDDnhan], [DCnhan], [SDTDDnhan], [IDKH]) VALUES (N'DD003     ', N'Tap hoa C', N'So 3, Mau Than, Can Tho', N'0353334444     ', N'KH003     ')
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH001     ', N'Coca Cola', 7, N'Thung', 10000, 20000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH002     ', N'Snack', 20, N'Goi', 6000, 10000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH003     ', N'Chicken', 15, N'Kg', 100000, 60000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH004     ', N'Milo', 10, N'Thung', 2000, 4000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH005     ', N'Bot ngot Vedan', 10, N'Thung', 50000, 10000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH006     ', N'Nuoc mam Hong Dai', 10, N'Thung', 300000, 600000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH007     ', N'Bot giat Omo', 10, N'Thung', 100000, 200000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH008     ', N'Clear', 30, N'Day', 20000, 40000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH009     ', N'Mi Hao Hao', 20, N'Thung', 100000, 200000)
INSERT [dbo].[HANGHOA] ([IDHH], [TenHH], [SLtoithieu], [DVT], [Dongianhap], [Dongiaxuat]) VALUES (N'HH010     ', N'Khan giay Bless You', 20, N'Cay', 50000, 100000)
INSERT [dbo].[KHACHHANG] ([IDKH], [TenKH], [SDTKH]) VALUES (N'KH001     ', N'Ta Nguyen Thuy Vy', N'0351234567     ')
INSERT [dbo].[KHACHHANG] ([IDKH], [TenKH], [SDTKH]) VALUES (N'KH002     ', N'Huynh Nhat Tan', N'0351231234     ')
INSERT [dbo].[KHACHHANG] ([IDKH], [TenKH], [SDTKH]) VALUES (N'KH003     ', N'Tran Minh Thang', N'0353214321     ')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'123       ', N'1234', NULL, NULL, NULL)
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC001     ', N'Cocacolahaha', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca1234@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC002     ', N'Oishi', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC003     ', N'KFC', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC004     ', N'Nestle', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC005     ', N'Vedan1', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC006     ', N'Hong Dai', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC007     ', N'Unilever Viet Nam', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC008     ', N'Acecook', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHACUNGCAP] ([IDnhaCC], [TennhaCC], [DiachinhaCC], [SDTnhaCC], [EmailnhaCC]) VALUES (N'CC009     ', N'Saigon Paper', N'485 Ha Noi, P. Linh Trung, Q. Thu Duc, TPHCM , Viet Nam', N'0283896100     ', N'Coca123@gmail.com')
INSERT [dbo].[NHANVIEN] ([IDNV], [TenNV], [Gioitinh], [DiachiNV], [SDTNV], [Ngaybatdaulam], [username], [password]) VALUES (N'NV001     ', N'Phan Le Trong Nghia', N'Nam    ', N'So 85/15 Nguyen Thong, Can Tho', N'0833361360     ', CAST(N'2019-10-02' AS Date), N'nghia', N'202cb962ac59075b964b07152d234b70')
INSERT [dbo].[NHANVIEN] ([IDNV], [TenNV], [Gioitinh], [DiachiNV], [SDTNV], [Ngaybatdaulam], [username], [password]) VALUES (N'NV002     ', N'Ngo Tuong Vinh', N'Nam    ', N'So 74 Nguyen Thong, Can Tho', N'0357772222     ', CAST(N'2019-10-03' AS Date), N'vinh', N'202cb962ac59075b964b07152d234b70')
INSERT [dbo].[NHANVIEN] ([IDNV], [TenNV], [Gioitinh], [DiachiNV], [SDTNV], [Ngaybatdaulam], [username], [password]) VALUES (N'NV003     ', N'Dang Minh Thuan', N'Nam    ', N'So 73 Nguyen Thong, Can Tho', N'0357773333     ', CAST(N'2019-10-02' AS Date), N'thuan', N'202cb962ac59075b964b07152d234b70')
INSERT [dbo].[TKADMIN] ([username], [password]) VALUES (N'thuan', N'202cb962ac59075b964b07152d234b70')
INSERT [dbo].[TRUC] ([IDNV], [STTca]) VALUES (N'NV001     ', N'1')
INSERT [dbo].[TRUC] ([IDNV], [STTca]) VALUES (N'NV002     ', N'2')
INSERT [dbo].[TRUC] ([IDNV], [STTca]) VALUES (N'NV003     ', N'3')
ALTER TABLE [dbo].[CHITIETKIEMKE]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETKIE__IDHH__3C69FB99] FOREIGN KEY([IDHH])
REFERENCES [dbo].[HANGHOA] ([IDHH])
GO
ALTER TABLE [dbo].[CHITIETKIEMKE] CHECK CONSTRAINT [FK__CHITIETKIE__IDHH__3C69FB99]
GO
ALTER TABLE [dbo].[CHITIETKIEMKE]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETKIE__IDNV__37A5467C] FOREIGN KEY([IDNV])
REFERENCES [dbo].[NHANVIEN] ([IDNV])
GO
ALTER TABLE [dbo].[CHITIETKIEMKE] CHECK CONSTRAINT [FK__CHITIETKIE__IDNV__37A5467C]
GO
ALTER TABLE [dbo].[CHITIETNHAPKHO]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETNHA__IDHH__30F848ED] FOREIGN KEY([IDHH])
REFERENCES [dbo].[HANGHOA] ([IDHH])
GO
ALTER TABLE [dbo].[CHITIETNHAPKHO] CHECK CONSTRAINT [FK__CHITIETNHA__IDHH__30F848ED]
GO
ALTER TABLE [dbo].[CHITIETNHAPKHO]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETNHA__IDNV__398D8EEE] FOREIGN KEY([IDNV])
REFERENCES [dbo].[NHANVIEN] ([IDNV])
GO
ALTER TABLE [dbo].[CHITIETNHAPKHO] CHECK CONSTRAINT [FK__CHITIETNHA__IDNV__398D8EEE]
GO
ALTER TABLE [dbo].[CHITIETXUATKHO]  WITH CHECK ADD FOREIGN KEY([IDDD])
REFERENCES [dbo].[DDNHAN] ([IDDD])
GO
ALTER TABLE [dbo].[CHITIETXUATKHO]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETXUA__IDHH__37A5467C] FOREIGN KEY([IDHH])
REFERENCES [dbo].[HANGHOA] ([IDHH])
GO
ALTER TABLE [dbo].[CHITIETXUATKHO] CHECK CONSTRAINT [FK__CHITIETXUA__IDHH__37A5467C]
GO
ALTER TABLE [dbo].[CHITIETXUATKHO]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETXUA__IDNV__3C69FB99] FOREIGN KEY([IDNV])
REFERENCES [dbo].[NHANVIEN] ([IDNV])
GO
ALTER TABLE [dbo].[CHITIETXUATKHO] CHECK CONSTRAINT [FK__CHITIETXUA__IDNV__3C69FB99]
GO
ALTER TABLE [dbo].[CUNGCAP]  WITH CHECK ADD  CONSTRAINT [FK__CUNGCAP__IDHH__2D27B809] FOREIGN KEY([IDHH])
REFERENCES [dbo].[HANGHOA] ([IDHH])
GO
ALTER TABLE [dbo].[CUNGCAP] CHECK CONSTRAINT [FK__CUNGCAP__IDHH__2D27B809]
GO
ALTER TABLE [dbo].[CUNGCAP]  WITH CHECK ADD  CONSTRAINT [FK__CUNGCAP__IDnhaCC__2E1BDC42] FOREIGN KEY([IDnhaCC])
REFERENCES [dbo].[NHACUNGCAP] ([IDnhaCC])
GO
ALTER TABLE [dbo].[CUNGCAP] CHECK CONSTRAINT [FK__CUNGCAP__IDnhaCC__2E1BDC42]
GO
ALTER TABLE [dbo].[DDNHAN]  WITH CHECK ADD FOREIGN KEY([IDKH])
REFERENCES [dbo].[KHACHHANG] ([IDKH])
GO
ALTER TABLE [dbo].[TRUC]  WITH CHECK ADD  CONSTRAINT [FK__TRUC__IDNV__403A8C7D] FOREIGN KEY([IDNV])
REFERENCES [dbo].[NHANVIEN] ([IDNV])
GO
ALTER TABLE [dbo].[TRUC] CHECK CONSTRAINT [FK__TRUC__IDNV__403A8C7D]
GO
ALTER TABLE [dbo].[TRUC]  WITH CHECK ADD  CONSTRAINT [FK__TRUC__STTca__412EB0B6] FOREIGN KEY([STTca])
REFERENCES [dbo].[CALAMVIEC] ([STTca])
GO
ALTER TABLE [dbo].[TRUC] CHECK CONSTRAINT [FK__TRUC__STTca__412EB0B6]
GO
USE [master]
GO
ALTER DATABASE [QLKHO] SET  READ_WRITE 
GO
