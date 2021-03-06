package com.diyalotech.datepicker.date

internal object Constants {
    const val adLBoundY = 1918
    const val adLBoundM = 4
    const val adLBoundD = 13

    const val bsLBoundY = 1975
    const val bsLBoundM = 1
    const val bsLBoundD = 1

    const val bsUBoundY = 2100
    const val bsUBoundM = 12
    const val bsUBoundD = 30

    const val adUBoundY = 2044
    const val adUBoundM = 4
    const val adUBoundD = 12

    internal val bsDaysInMonthByYear = hashMapOf(
        bsLBoundY to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        1976 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        1977 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 365),
        1978 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1979 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        1980 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        1981 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        1982 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1983 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        1984 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        1985 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        1986 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1987 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        1988 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        1989 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        1990 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1991 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30, 365),
        1992 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        1993 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1994 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1995 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30, 365),
        1996 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        1997 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1998 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        1999 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2000 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 365),
        2001 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2002 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2003 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2004 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 30, 29, 29, 31, 365),
        2005 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2006 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2007 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2008 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31, 365),
        2009 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2010 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2011 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2012 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        2013 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2014 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2015 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2016 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        2017 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2018 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2019 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        2020 to intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2021 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2022 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30, 365),
        2023 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        2024 to intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2025 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2026 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2027 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 365),
        2028 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2029 to intArrayOf(31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30, 365),
        2030 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2031 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 365),
        2032 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2033 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2034 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2035 to intArrayOf(30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31, 365),
        2036 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2037 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2038 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2039 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        2040 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2041 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2042 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2043 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        2044 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2045 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2046 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2047 to intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2048 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2049 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30, 365),
        2050 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        2051 to intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2052 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2053 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30, 365),
        2054 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        2055 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2056 to intArrayOf(31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30, 365),
        2057 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2058 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 365),
        2059 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2060 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2061 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2062 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31, 365),
        2063 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2064 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2065 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2066 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31, 365),
        2067 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2068 to intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2069 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2070 to intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30, 365),
        2071 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2072 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30, 365),
        2073 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31, 366),
        2074 to intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2075 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2076 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30, 365),
        2077 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        2078 to intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2079 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30, 365),
        2080 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30, 365),
        2081 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31, 366),
        2082 to intArrayOf(31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2083 to intArrayOf(31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2084 to intArrayOf(31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2085 to intArrayOf(31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30, 366),
        2086 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2087 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 30, 30, 30, 366),
        2088 to intArrayOf(30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30, 365),
        2089 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2090 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2091 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30, 366),
        2092 to intArrayOf(30, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2093 to intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2094 to intArrayOf(31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30, 365),
        2095 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 30, 30, 30, 366),
        2096 to intArrayOf(30, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30, 364),
        2097 to intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30, 366),
        2098 to intArrayOf(31, 31, 32, 31, 31, 31, 29, 30, 29, 30, 29, 31, 365),
        2099 to intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 29, 30, 30, 30, 365),
        bsUBoundY to intArrayOf(31, 32, 31, 32, 30, 31, 30, 29, 30, 29, 30, 30, 365)
    )
}