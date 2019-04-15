package com.example.data.source.remote

import com.example.core.exception.DataSourceException

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source")